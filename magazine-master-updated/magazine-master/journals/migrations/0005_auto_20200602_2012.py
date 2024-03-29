# Generated by Django 3.0b1 on 2020-06-02 15:12

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('journals', '0004_auto_20200601_1134'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='journal',
            options={'ordering': ['-created_at']},
        ),
        migrations.CreateModel(
            name='AdminComments',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('body', models.TextField(default=None)),
                ('created_on', models.DateTimeField(auto_now=True)),
                ('admin', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
                ('journal', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='admin_comments', to='journals.Journal')),
            ],
            options={
                'ordering': ['created_on'],
            },
        ),
    ]
