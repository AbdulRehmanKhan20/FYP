from django.db import models
from user_management.models import User
from enum import Enum


# ENUM FOR THE STATUS to be used
class JournalStatus(Enum):
    PENDING = "Pending"
    PUBLISHED = "Published"
    REJECTED = "Rejected"


# Journals app models are enlisted below
class Journal(models.Model):
    title = models.CharField(max_length=100, unique=True)
    body = models.TextField(default="", null=True)
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.CharField(
        max_length=10,
        choices=[
            (tag, tag.value) for tag in JournalStatus
        ], default='PENDING'
    )
    published_at = models.DateTimeField(null=True, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
    last_updated = models.DateTimeField(auto_now=True)


# Approver's Comment
class Comments(models.Model):
    journal = models.ForeignKey(Journal, on_delete=models.CASCADE, related_name='comments')
    comment = models.TextField(default="", blank=False)
    commented_at = models.DateTimeField(auto_now=True)
    publisher = models.ForeignKey(User, on_delete=models.CASCADE, related_name='comments')
