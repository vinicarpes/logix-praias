document.addEventListener('DOMContentLoaded', () => {
    // Exclusão
    document.querySelectorAll('.btn-danger.btn-sm').forEach(btn => {
        btn.addEventListener('click', function (event) {
            event.preventDefault();
            const id = this.getAttribute('data-id');

            if (!id) {
                console.error("ID não encontrado no botão.");
                return;
            }

            if (!confirm("Tem certeza que deseja excluir este usuário?")) {
                return;
            }

            fetch(`/user/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then(response => {
                    if (response.ok) {
                        const row = this.closest('tr');
                        if (row) row.remove();
                    } else {
                        alert("Erro ao excluir usuário.");
                    }
                })
                .catch(error => {
                    console.error("Erro na requisição:", error);
                    alert("Erro inesperado ao tentar excluir.");
                });
        });
    });


    const form = document.getElementById('form-usuario');

    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault();

            const idInput = form.getAttribute('data-edit-id');


            const nome = document.getElementById('nome').value;
            const mtcl = document.getElementById('mtcl').value;
            const perfil = document.getElementById('perfil').value;
            const graduacao = document.getElementById('user-grad').value;
            const setor = document.getElementById('user-beach').value;
            const telefone = document.getElementById('user-phone').value;
            const email = document.getElementById('user-email').value;

            if (!nome || !mtcl || !perfil || !graduacao || !setor || !telefone || !email) {
                alert("Preencha todos os campos obrigatórios.");
                return;
            }

            const usuario = {
                nome,
                mtcl,
                perfil,
                graduacao,
                setor,
                telefone,
                email
            };

            if (idInput) {
                // Atualiza
                fetch(`/user/update/${idInput}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(usuario)
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Usuário atualizado com sucesso!");
                            location.reload();
                        } else {
                            alert("Erro ao atualizar usuário.");
                        }
                    })
                    .catch(err => {
                        console.error("Erro:", err);
                        alert("Erro inesperado ao atualizar.");
                    });
            } else {
                // Criação
                this.submit();
            }
        });
    }
});

// Preencher formulário para edição
function editarUsuario(botao) {
    const id = botao.getAttribute('data-id');

    if (!id) {
        console.error("ID ausente no botão de edição.");
        return;
    }

    fetch(`/user/get/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar usuário.");
            return response.json();
        })
        .then(usuario => {
            document.getElementById('nome').value = usuario.nome || '';
            document.getElementById('mtcl').value = usuario.mtcl || '';
            document.getElementById('perfil').value = usuario.perfil || '';
            document.getElementById('user-grad').value = usuario.graduacao || '';
            document.getElementById('user-beach').value = usuario.setor || '';
            document.getElementById('user-phone').value = usuario.telefone || '';
            document.getElementById('user-email').value = usuario.email || '';

            const form = document.getElementById('form-usuario');
            form.setAttribute('data-edit-id', usuario.id);
            form.setAttribute('action', `/user/update/${usuario.id}`);
        })
        .catch(error => {
            console.error("Erro ao carregar dados do usuário:", error);
            alert("Não foi possível carregar os dados do usuário.");
        });
}
