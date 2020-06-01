from django.views import View
from django.utils.decorators import method_decorator
from django.shortcuts import render, redirect
from journals import models, forms
from django.core.paginator import Paginator, PageNotAnInteger, EmptyPage
from django.contrib.auth.decorators import login_required
from user_management.commons import has_admin_group


# List published journals
@method_decorator(login_required, name='get')
class ListPublishedJournals(View):
    def get(self, request):
        user = request.user
        journals_list = models.Journal.objects.filter(status__exact='Published', author=user)
        if has_admin_group(user):
            journals_list = models.Journal.objects.filter(status__exact='Published')
        page = request.GET.get('page', 1)
        paginator = Paginator(journals_list, 9)

        try:
            journals = paginator.page(page)
        except PageNotAnInteger:
            journals = paginator.page(1)
        except EmptyPage:
            journals = paginator.page(paginator.num_pages)

        context = {
            'journals_list': journals,
            'page_type': 'List of Approved Journals:'
        }

        return render(request, 'journals/list-journals.html', context)


# List pending journals
@method_decorator(login_required, name='get')
class ListUsersPendingJournals(View):
    def get(self, request):
        user = request.user
        journals_list = models.Journal.objects.filter(status__exact='PENDING', author=user)
        if has_admin_group(user):
            journals_list = models.Journal.objects.filter(status__exact='PENDING')

        page = request.GET.get('page', 1)
        paginator = Paginator(journals_list, 9)

        try:
            journals = paginator.page(page)
        except PageNotAnInteger:
            journals = paginator.page(1)
        except EmptyPage:
            journals = paginator.page(paginator.num_pages)

        context = {
            'journals_list': journals,
            'page_type': 'List of Pending Journals:'
        }

        return render(request, 'journals/list-pending-journals.html', context)

# List rejected journals
@method_decorator(login_required, name='get')
class ListRejectedJournals(View):
    def get(self, request):
        user = request.user
        journals_list = models.Journal.objects.filter(status__exact='Rejected', author=user)
        if has_admin_group(user):
            journals_list = models.Journal.objects.filter(status__exact='Rejected')
        page = request.GET.get('page', 1)
        paginator = Paginator(journals_list, 9)

        try:
            journals = paginator.page(page)
        except PageNotAnInteger:
            journals = paginator.page(1)
        except EmptyPage:
            journals = paginator.page(paginator.num_pages)

        context = {
            'journals_list': journals,
            'page_type': 'List of Rejected Journals:'
        }

        return render(request, 'journals/list-rejected-journals.html', context)


# List rejected journals
@method_decorator(login_required, name='get')
class AddJournal(View):
    context = {'page_title': 'Post New Journal'}

    def get(self, request):
        form = forms.AddJournalForm()
        self.context['form'] = form

        return render(request, 'journals/add-journal.html', self.context)

    def post(self, request):
        form = forms.AddJournalForm(request.POST)
        self.context['form'] = form
        if form.is_valid():
            journal = models.Journal()
            journal.title = form.cleaned_data['title']
            journal.body = form.cleaned_data['body']
            journal.author = request.user
            journal.save()

            return redirect('Journals:list_published_journals')
        # else return to same page with errors
        return render(request, 'journals/add-journal.html', self.context)