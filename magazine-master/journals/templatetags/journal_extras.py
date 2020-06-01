from django import template
from user_management.commons import has_admin_group

register = template.Library()


@register.filter
def has_admin_group(value):
    return has_admin_group(value)
