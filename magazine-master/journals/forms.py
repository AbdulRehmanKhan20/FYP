from django import forms
from journals import models


class AddJournalForm(forms.ModelForm):
    class Meta:
        model = models.Journal
        fields = ['title', 'body']

    def __init__(self, *args, **kwargs):
        super(AddJournalForm, self).__init__(*args, **kwargs)
        for key, value in self.fields.items():
            self.fields[key].widget.attrs.update({'class': 'form-control'})
            self.fields[key].widget.attrs.update({'placeholder': key})