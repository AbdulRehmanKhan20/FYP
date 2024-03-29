# Generated by Django 3.0b1 on 2020-06-05 20:25

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('journals', '0005_auto_20200602_2012'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='comments',
            name='active',
        ),
        migrations.RemoveField(
            model_name='comments',
            name='email',
        ),
        migrations.RemoveField(
            model_name='comments',
            name='name',
        ),
        migrations.AddField(
            model_name='comments',
            name='user',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
            preserve_default=False,
        ),
    ]
