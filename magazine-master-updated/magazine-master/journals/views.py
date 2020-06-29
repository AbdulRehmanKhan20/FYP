from django.views import View
from django.utils.decorators import method_decorator
from django.shortcuts import render, redirect, get_object_or_404
from journals import models, forms
from django.core.paginator import Paginator, PageNotAnInteger, EmptyPage
from django.contrib.auth.decorators import login_required
from user_management.commons import has_admin_group
from django.urls import resolve


# List published journals
@method_decorator(login_required, name='get')
class ListPublishedJournals(View):
    def get(self, request):
        user = request.user
        journals_list = models.Journal.objects.filter(status__exact='Published')
        if has_admin_group(user):
            journals_list = models.Journal.objects.filter(status__exact='Published')
        page = request.GET.get('page', 1)
        paginator = Paginator(journals_list, 2)

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
    context = {
        'page_title': 'Add Journal',
        'form_type': 'add'
    }

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


# List rejected journals
@method_decorator(login_required, name='get')
class EditJournal(View):
    context = {
        'page_title': 'Update Journal',
        'form_type': 'update'
    }

    def get(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        form = forms.AddJournalForm(instance=journal)
        self.context['form'] = form

        return render(request, 'journals/add-journal.html', self.context)

    def post(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        form = forms.AddJournalForm(request.POST, instance=journal)
        if form.is_valid():
            journal.title = form.cleaned_data['title']
            journal.body = form.cleaned_data['body']
            journal.status = 'Pending'
            journal.save()

            return redirect('Journals:list_pending_journals')
        # else return to same page with errors
        return render(request, 'journals/add-journal.html', self.context)


class ViewJournal(View):
    def get(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        comments = journal.comments.all()
        comment_form = forms.CommentForm()
        context = {
            'journal': journal,
            'comments': comments,
            'comment_form': comment_form
        }

        return render(request, 'staticpages/view-journal.html', context)

    def post(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        comments = journal.comments.all()
        new_comment = None
        # Comment posted
        comment_form = forms.CommentForm(data=request.POST)

        if comment_form.is_valid():
            # Create Comment object but don't save to database yet
            new_comment = comment_form.save(commit=False)
            # Assign the current post to the comment
            new_comment.journal = journal
            # Assign Current User as admin
            new_comment.user = request.user
            # Save the comment to the database
            new_comment.save()
            # Empty the form
            comment_form = forms.CommentForm()

        return render(request, 'staticpages/view-journal.html', {
            'journal': journal,
            'comments': comments,
            'new_comment': new_comment,
            'comment_form': comment_form
        })


# View for admin only
@method_decorator(login_required, name='get')
class ViewJournalAdmin(View):
    def get(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        comments = journal.admin_comments.all()
        comment_form = forms.AdminCommentForm()
        context = {
            'journal': journal,
            'comments': comments,
            'comment_form': comment_form
        }

        return render(request, 'journals/show-journal-admin.html', context)

    def post(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        comments = journal.admin_comments.all()
        new_comment = None
        # Comment posted
        comment_form = forms.AdminCommentForm(data=request.POST)

        if comment_form.is_valid():
            # Create Comment object but don't save to database yet
            new_comment = comment_form.save(commit=False)
            # Assign the current post to the comment
            new_comment.journal = journal
            # Assign Current User as admin
            new_comment.admin = request.user
            # Save the comment to the database
            new_comment.save()
            # Empty the form
            comment_form = forms.AdminCommentForm()

        return render(request, 'journals/show-journal-admin.html', {
            'journal': journal,
            'comments': comments,
            'new_comment': new_comment,
            'comment_form': comment_form
        })


class ApproveJournal(View):
    def get(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        journal.status = 'Published'
        journal.save()

        return redirect('Journals:list_pending_journals')


class RejectJournal(View):
    def get(self, request, journal_id):
        journal = get_object_or_404(models.Journal, id=journal_id)
        journal.status = 'Rejected'
        journal.save()

        return redirect('Journals:list_pending_journals')


# Get list of all published journals for homepage
def list_published_journals_for_homepage(request):
    # resolve the url from the path
    url_name = resolve(request.path).url_name
    template_name = 'FrontEnd/home.html' if url_name == 'home' else 'FrontEnd/mainhome.html'
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
    }

    return render(request, template_name, context)
