# Generated by Django 3.0b1 on 2020-06-29 10:11

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('auditorium', '0004_auto_20200625_1232'),
    ]

    operations = [
        migrations.AlterField(
            model_name='auditoriumreservation',
            name='requestor',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
        ),
    ]
