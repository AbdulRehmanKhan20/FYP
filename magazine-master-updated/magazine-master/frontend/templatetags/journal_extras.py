from django import template

register = template.Library()


@register.filter(takes_context=True)
def has_admin_group(value):
    is_admin = False
    if value.groups.filter(name='Admins').count() > 0:
        is_admin = True
    return is_admin
