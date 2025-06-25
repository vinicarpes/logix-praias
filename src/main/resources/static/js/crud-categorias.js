document.addEventListener('DOMContentLoaded', () => {
    // Botão EXCLUIR
    document.querySelectorAll('.btn-danger.btn-sm').forEach(btn => {
        btn.addEventListener('click', function (event) {
            event.preventDefault();
            const id = this.getAttribute('data-id');

            if (!id) {
                console.error("ID não encontrado no botão.");
                return;
            }

            if (!confirm("Tem certeza que deseja excluir este material?")) {
                return;
            }

            fetch(`/material/type/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                }
            })
                .then(response => {
                    if (response.ok) {
                        const row = this.closest('tr');
                        if (row) row.remove();
                        else console.warn("Linha da tabela não encontrada.");
                    } else {
                        alert("Não é possível excluir esta categoria pois ela é referenciada por algum material!");
                    }
                })
                .catch(error => {
                    console.error("Erro na requisição:", error);
                    alert("Erro inesperado ao tentar excluir.");
                });
        });
    });

    // Formulário: modo edição com PUT
    const form = document.getElementById('form-categoria');

    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault(); // impede o envio padrão

            const idInput = form.getAttribute('data-edit-id'); // ID da edição (se tiver)
            const nome = form.querySelector('[name="nome"]').value;
            const descricao = form.querySelector('[name="descricao"]').value;

            const categoria = { nome, descricao };

            if (idInput) {
                // Atualiza categoria via PUT
                fetch(`/material/type/update/${idInput}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(categoria)
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Categoria atualizada com sucesso!");
                            location.reload();
                        } else {
                            alert("Erro ao atualizar categoria.");
                        }
                    })
                    .catch(err => {
                        console.error("Erro:", err);
                        alert("Erro inesperado.");
                    });
            } else {
                // Se não está editando, submete normalmente para salvar novo
                this.submit();
            }
        });
    }
});

// Função chamada ao clicar no botão "Editar"
function editarCategoria(botao) {
    const id = botao.getAttribute('data-id');

    fetch(`/material/type/${id}`) // <-- Corrigido aqui
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar categoria.");
            return response.json();
        })
        .then(categoria => {
            document.querySelector('[name="nome"]').value = categoria.nome;
            document.querySelector('[name="descricao"]').value = categoria.descricao;
            document.getElementById('form-categoria').setAttribute('data-edit-id', categoria.id);
        })
        .catch(error => {
            console.error(error);
            alert("Não foi possível carregar os dados da categoria.");
        });
}
