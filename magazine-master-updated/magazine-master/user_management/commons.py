# Check if user is from admin group
def has_admin_group(user):
    if user:
        return user.groups.filter(name='Admins').count() > 0
    return False
