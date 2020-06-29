from django.urls import path
from . import views
from journals import views

app_name = 'Journals'
urlpatterns = [
    path('', views.ListPublishedJournals.as_view(), name='list_all_journals'),
    path('list_published_journals', views.ListPublishedJournals.as_view(), name='list_published_journals'),
    path('list_pending_journals', views.ListUsersPendingJournals.as_view(), name='list_pending_journals'),
    path('list_rejected_journals', views.ListRejectedJournals.as_view(), name='list_rejected_journals'),
    path('add_journal', views.AddJournal.as_view(), name='add_journal'),
    path(r'edit_journal/<int:journal_id>', views.EditJournal.as_view(), name='edit_journal'),
    path(r'approve_journal/<int:journal_id>', views.ApproveJournal.as_view(), name='approve_journal'),
    path(r'reject_journal/<int:journal_id>', views.RejectJournal.as_view(), name='reject_journal'),
    path(r'view_journal/<int:journal_id>', views.ViewJournal.as_view(), name='view_journal'),
    path(r'view_journal_admin/<int:journal_id>', views.ViewJournalAdmin.as_view(), name='view_journal_admin'),
]