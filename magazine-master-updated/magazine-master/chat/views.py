import pdb
from django.shortcuts import render, redirect
from django.db.models import Q
from chat import models
from django.contrib.auth.decorators import login_required
from chat.forms import AddRoomForm


@login_required
def index(request):
    current_user = request.user
    available_rooms = models.Room.objects.filter(~Q(roomuser_room__user=current_user))
    joined_rooms = models.Room.objects.filter(roomuser_room__user=current_user)
    page_data = {
        'rooms': available_rooms,
        'joined_rooms': joined_rooms
    }

    return render(request, 'Chatroom/index.html', page_data)


@login_required
def open_chat_room(request, room_id):
    current_user = request.user
    room = models.Room.objects.get(pk=room_id)
    chats = models.Chat.objects.filter(room=room)
    # Check if already joined then do not insert
    new_user = models.RoomUser.objects.filter(room=room, user=current_user)
    if not new_user.exists():
        room_user = models.RoomUser(room=room, user=current_user)
        room_user.save()
    room_users_list = models.RoomUser.objects.filter(room=room)

    page_data = {
        'room': room,
        'chats': chats,
        'room_users_list': room_users_list
    }

    return render(request, 'Chatroom/room.html', page_data)


@login_required
def leave_chat_room(request, room_id):
    current_user = request.user
    room = models.Room(pk=room_id)
    models.RoomUser.objects.get(room=room, user=current_user).delete()

    return redirect('Chat:index')


@login_required
def add_room(request):
    context = {'page_title': 'Add Room'}

    if request.method == 'GET':
        form = AddRoomForm()
        context['form'] = form

        return render(request, 'Chatroom/add-room.html', context)

    elif request.method == 'POST':
        form = AddRoomForm(request.POST)
        if form.is_valid():
            form.save()

            return redirect('Chat:index')
        # else return to same page with errors
        return render(request, 'Chatroom/add-room.html', context)
