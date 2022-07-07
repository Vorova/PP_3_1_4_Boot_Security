const API = "/admin/api/"

async function showEditModal(id) {
    let user = await getUser(id);
    console.log(user);
    document.getElementById("editId").value = user.id;
    document.getElementById("editUsername").value = user.username;
    document.getElementById("editEmail").value = user.email;
}

async function getUser(id) {
    let response = await fetch(API + "getUser/" + id);
    return await response.json();
}