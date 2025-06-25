document.addEventListener('DOMContentLoaded', () => {
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

            fetch(`/material/delete/${id}`, {
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
                        alert("Erro ao excluir material.");
                    }
                })
                .catch(error => {
                    console.error("Erro na requisição:", error);
                    alert("Erro inesperado ao tentar excluir.");
                });
        });
    });
});

// Formulário: modo edição com PUT
const form = document.getElementById('form-material');

if (form) {
    form.addEventListener('submit', function (event) {
        event.preventDefault(); // impede o envio padrão

        const idInput = form.getAttribute('data-edit-id'); // ID da edição (se tiver)
        const nome = form.querySelector('[name="nome"]').value;
        const descricao = form.querySelector('[name="descricao"]').value;
        const categoriaId = form.querySelector('[name="categoria"]').value;

        // Verificações simples
        if (!nome || !categoriaId) {
            alert("Preencha todos os campos obrigatórios.");
            return;
        }

        const material = {
            nome,
            descricao,
            categoria: { id: categoriaId }
        };

        if (idInput) {
            // Atualiza material via PUT
            fetch(`/material/update/${idInput}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(material)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Material atualizado com sucesso!");
                        location.reload();
                    } else {
                        alert("Erro ao atualizar material.");
                    }
                })
                .catch(err => {
                    console.error("Erro:", err);
                    alert("Erro inesperado.");
                });
        } else {
            // Se não está editando, submete normalmente (POST)
            this.submit();
        }
    });
}

// Função chamada ao clicar no botão "Editar"
function editarMaterial(botao) {
    const id = botao.getAttribute('data-id');

    fetch(`/material/get/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar material.");
            return response.json();
        })
        .then(material => {
            // Preenche os campos
            document.querySelector('[name="nome"]').value = material.nome;
            document.querySelector('[name="descricao"]').value = material.descricao;
            const select = document.querySelector('#categoria');

            if (select) {
                select.value = material.categoria.id;
            }

            // Marca o formulário como modo edição
            const form = document.getElementById('form-material');
            if (form) {
                form.setAttribute('data-edit-id', material.id);
            }
        })
        .catch(error => {
            console.error(error);
            alert("Não foi possível carregar os dados do material.");
        });
}

