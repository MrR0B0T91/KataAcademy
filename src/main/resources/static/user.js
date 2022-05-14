$(async function () {
    await getUser();
})

const userService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findUserAuntificated: async () => await fetch('api/admin/user')
}


async function getUser() {
    let table = $('#mainUser tbody')
    table.empty()

    await userService.findUserAuntificated()
        .then(res => res.json())
        .then(user => {
            let tableMainUser = `$(
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td id="roleName">
                </td>           
            </tr>
            )`;
            table.append(tableMainUser);

            let userMenu = $('#userMenu')
            let userName = `${user.username}`
            userMenu.append(userName)

            let roleMenu = $('#roleMenu')
            user.roles.forEach(role => {
                let roleList = `${role.name}`;
                roleMenu.append(roleList)
            })

            let roleName = $('#roleName')
            user.roles.forEach(role => {
                let roleList = `${role.name}`;
                roleName.append(roleList)
            })

        })
}