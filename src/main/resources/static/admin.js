$(async function () {
    await getUsersTable();
    await addUser();
    await getDefaultModal()
})

const userService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    getUsers: async () => await fetch('api/admin'),
    findUser: async (id) => await fetch(`api/admin/${id}`),
    findCurrentUser: async () => await fetch('api/admin/user'),
    addNewUser: async (user) => await fetch('api/admin', {
        method: 'POST',
        headers: userService.head,
        body: JSON.stringify(user)
    }),
    deleteUser: async (id) => await fetch(`api/admin/${id}`, {method: 'DELETE', headers: userService.head}),
    updateUser: async (user, id) => await fetch(`api/admin/${id}`, {
        method: 'PUT',
        headers: userService.head,
        body: JSON.stringify(user)
    })
}

async function getUsersTable() {
    let table = $('#mainUser tbody');
    table.empty();

    await userService.getUsers()
        .then(res => res.json())
        .then(users => {
            users.forEach(user => {
                let tableMainUser = `$(
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td name="roleName">
                </td>
                <td>
                     <button type="button" data-userid="${user.id}" data-action="edit" class="btn btn-info"
                            data-toggle="modal" data-target="#modal">Edit</button>
                </td>
                <td>
                     <button type="button" data-userid="${user.id}" data-action="delete" class="btn btn-danger"
                             data-toggle="modal" data-target="#modal">Delete</button>
                </td>
            </tr>
            )`;
                table.append(tableMainUser);
                let setRole = document.getElementsByName('roleName');
                let roleAdd = Array.from(setRole).pop();
                user.roles.forEach(role => {
                    let roleName = role.name + " ";
                    roleAdd.append(roleName)
                })
            })
        })
    let currentUser = await userService.findCurrentUser();
    let user = currentUser.json();
    user.then(userAut => {
        let userMenu = $("#userMenu");
        userMenu.empty();
        let userName = userAut.username;
        userMenu.append(userName);

        let roleMenu = $('#roleMenu');
        roleMenu.empty();
        userAut.roles.forEach(role => {
            let roleName = role.name + ' ';
            roleMenu.append(roleName);
        })
    })
    $("#mainUser").find('button').on('click', (event) => {
        let defaultModal = $('#modal');

        let targetButton = $(event.target);
        let buttonUserId = targetButton.attr('data-userid');
        let buttonAction = targetButton.attr('data-action');

        defaultModal.attr('data-userid', buttonUserId);
        defaultModal.attr('data-action', buttonAction);
        defaultModal.modal('show');
    })
}

async function getDefaultModal() {
    $('#modal').modal({
        keyboard: true,
        backdrop: "static",
        show: false
    }).on("show.bs.modal", (event) => {
        let thisModal = $(event.target);
        let userid = thisModal.attr('data-userid');
        let action = thisModal.attr('data-action');
        switch (action) {
            case 'edit':
                editUser(thisModal, userid);
                break;
            case 'delete':
                deleteUser(thisModal, userid);
                break;
        }
    }).on("hidden.bs.modal", (e) => {
        let thisModal = $(e.target);
        thisModal.find('.modal-title').html('');
        thisModal.find('.modal-body').html('');
        thisModal.find('.modal-footer').html('');
    })
}

async function editUser(modal, id) {
    let findUser = await userService.findUser(id);
    let user = findUser.json();

    modal.find('.modal-title').html('Edit USER');

    let editButton = `<button type="button" id="editButton" class="btn btn-info">Edit</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(closeButton);
    modal.find('.modal-footer').append(editButton);

    user.then(user => {
        let dataRole = [{
            id: 1,
            name: 'ROLE_ADMIN'
        }, {
            id: 2,
            name: 'ROLE_USER'
        }]
        let bodyForm = `
             <form role="form" class="form-horizontal" id="editUser">
                        <div class="form-group">
                            <label for="id">ID
                                <input type="number" class="form-control" name="id" id="id" value="${user.id}"
                                       readonly="readonly">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Name
                                <input type="text" class="form-control" id="name" value="${user.name}" 
                                       name="name">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Surname
                                <input type="text" class="form-control" id="surname" value="${user.surname}"
                                       name="lastName">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Age
                                <input type="number" class="form-control" id="age" value="${user.age}" name="age">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Username
                                <input type="text" class="form-control" id="username" value="${user.username}" name="email">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Password
                                <input type="password" class="form-control" id="password" value="${user.password}"
                                       name="password">
                            </label>
                        </div>
                        <div class="d-flex flex-row bd-highlight">
                            <div class="form-group">
                                <label for="roleList">Role:
                                    <select id="roleList" class="custom-select bd-primary"
                                            size="2" name="roleList" multiple="multiple">
                                            <option value="${dataRole[0].id}" >${dataRole[0].name}</option>
                                            <option value="${dataRole[1].id}" >${dataRole[1].name}</option>
                                       
                                    </select>
                                </label>
                            </div>
                        </div>         
        `;
        modal.find('.modal-body').append(bodyForm);
        user.roles.forEach(role => {
            $('#roleList').val(role.id);

        })
    })

    $("#editButton").on('click', async () => {
        let id = modal.find("#id").val();
        let name = modal.find('#name').val();
        let surname = modal.find('#surname').val();
        let username = modal.find('#username').val();
        let password = modal.find('#password').val();
        let age = modal.find('#age').val();
        let roles = modal.find('#roleList').val();
        let data = {
            id: id,
            name: name,
            surname: surname,
            age: age,
            username: username,
            password: password,
            roles: roles
        }
        console.log(data)
        await userService.updateUser(data, id);
        await getUsersTable();
        modal.modal('hide');
    })
}

async function deleteUser(modal, id) {
    let findUser = await userService.findUser(id);
    let user = findUser.json();

    modal.find('.modal-title').html('Delete USER');

    let deleteButton = `<button type="button" id="deleteButton" class="btn btn-info">Delete</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(closeButton);
    modal.find('.modal-footer').append(deleteButton);

    user.then(user => {
        let dataRole = [{
            id: 1,
            name: 'ROLE_ADMIN'
        }, {
            id: 2,
            name: 'ROLE_USER'
        }]
        let bodyForm = `
             <form role="form" class="form-horizontal" id="editUser">
                        <div class="form-group">
                            <label for="id">ID
                                <input type="number" class="form-control" name="id" id="id" value="${user.id}"
                                       readonly="readonly">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Name
                                <input type="text" class="form-control" id="name" value="${user.name}" 
                                       name="name" readonly="readonly">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Surname
                                <input type="text" class="form-control" id="lastName" value="${user.surname}"
                                       name="lastName" readonly="readonly">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Age
                                <input type="number" class="form-control" id="age" value="${user.age}" name="age" readonly="readonly">
                            </label>
                        </div>
                        <div class="form-group">
                            <label>Username
                                <input type="text" class="form-control" id="email" value="${user.username}" name="email" readonly="readonly">
                            </label>
                        </div>
                        <div class="d-flex flex-row bd-highlight">
                            <div class="form-group">
                                <label for="roleList">Role:
                                    <select id="roleList" class="custom-select bd-primary"
                                            size="2" name="roleList" multiple="multiple" style="width: 210px" disabled="disabled">
                                            <option value="${dataRole[0].id}" >${dataRole[0].name}</option>
                                            <option value="${dataRole[1].id}" >${dataRole[1].name}</option>
                                       
                                    </select>
                                </label>
                            </div>
                        </div>         
        `;
        modal.find('.modal-body').append(bodyForm);
        user.roles.forEach(role => {
            $('#roleList').val(role.id);
        })
    })
    $("#deleteButton").on('click', async () => {
        await userService.deleteUser(id);
        await getUsersTable();
        modal.modal('hide');
    })
}

async function addUser() {
    $('#addNewUserButton').click(async () => {
        let addUserForm = $('#defaultSomeForm')
        let name = addUserForm.find('#name').val();
        let surname = addUserForm.find('#surname').val();
        let username = addUserForm.find('#username').val();
        let password = addUserForm.find('#password').val();
        let age = addUserForm.find('#age').val();
        let roles = addUserForm.find('#newRole').val();
        let data = {
            name: name,
            surname: surname,
            age: age,
            username: username,
            password: password,
            roles: roles
        }
        await userService.addNewUser(data);
        await getUsersTable();
        addUserForm.find('#name').val('');
        addUserForm.find('#surname').val('');
        addUserForm.find('#age').val('');
        addUserForm.find('#username').val('');
        addUserForm.find('#password').val('');
        addUserForm.find('#newRole').val('');
        document.getElementById('nav-home-tab').click()
    })
}