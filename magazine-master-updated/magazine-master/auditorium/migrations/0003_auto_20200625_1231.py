# Generated by Django 3.0b1 on 2020-06-25 07:31

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('auditorium', '0002_auditoriumreservation_requestor'),
    ]

    operations = [
        migrations.AlterField(
            model_name='auditoriumreservation',
            name='status',
            field=models.TextField(),
        ),
    ]
