# Generated by Django 3.0b1 on 2020-03-26 10:02

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion
import journals.models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Journal',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=100, unique=True)),
                ('body', models.TextField(default='', null=True)),
                ('status', models.CharField(choices=[(journals.models.JournalStatus['PENDING'], 'Pending'), (journals.models.JournalStatus['PUBLISHED'], 'Published'), (journals.models.JournalStatus['REJECTED'], 'Rejected')], default='PENDING', max_length=10)),
                ('published_at', models.DateTimeField(blank=True, null=True)),
                ('created_at', models.DateTimeField(auto_now_add=True)),
                ('last_updated', models.DateTimeField(auto_now=True)),
                ('author', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='Comments',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('comment', models.TextField(default='')),
                ('commented_at', models.DateTimeField(auto_now=True)),
                ('journal', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='comments', to='journals.Journal')),
                ('publisher', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='comments', to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
