from django.urls import path
from . import views
from journals.views import ListPublishedJournals, AddJournal, ListRejectedJournals, ListUsersPendingJournals

app_name = 'Journals'
urlpatterns = [
    path('', ListPublishedJournals.as_view(), name='list_all_journals'),
    path('list_published_journals', ListPublishedJournals.as_view(), name='list_published_journals'),
    path('list_pending_journals', ListUsersPendingJournals.as_view(), name='list_pending_journals'),
    path('list_rejected_journals', ListRejectedJournals.as_view(), name='list_rejected_journals'),
    path('add_journal', AddJournal.as_view(), name='add_journal'),
]