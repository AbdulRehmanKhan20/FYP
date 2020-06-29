from django import forms
from journals import models
from crispy_forms.helper import FormHelper
from django_summernote.widgets import SummernoteWidget, SummernoteInplaceWidget


class AddJournalForm(forms.ModelForm):
    body = forms.CharField(widget=SummernoteWidget())

    class Meta:
        model = models.Journal
        fields = ['title', 'body']


class CommentForm(forms.ModelForm):
    class Meta:
        model = models.Comments
        fields = ('body',)

    def __init__(self, *args, **kwargs):
        super(CommentForm, self).__init__(*args, **kwargs)
        self.helper = FormHelper()
        self.helper.form_show_labels = False
        self.fields['body'].widget.attrs.update({'style': 'height: 140px;resize: none;margin-top:-10px;'})


class AdminCommentForm(forms.ModelForm):
    class Meta:
        model = models.AdminComments
        fields = ('body',)

    def __init__(self, *args, **kwargs):
        super(AdminCommentForm, self).__init__(*args, **kwargs)
        self.helper = FormHelper()
        self.helper.form_show_labels = False
        self.fields['body'].widget.attrs.update({'style': 'height: 140px;resize: none;margin-top:-10px;'})
