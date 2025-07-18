document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('form-retirada');

    if (form) {
        form.addEventListener('submit', function (event) {
            const id = form.getAttribute('data-edit-id');

            if (!id) {
                // Se não estiver em modo de edição, envia normalmente para o backend
                return; // deixa o comportamento padrão seguir
            }

            event.preventDefault(); // só impede o submit se for edição

            const materialField = document.getElementById('material');
            const usuarioField = document.getElementById('resp-withdrawal');
            const quantidadeField = document.getElementById('quantity');
            const observacaoField = document.getElementById('observation');

            if (!materialField || !usuarioField || !quantidadeField || !observacaoField) {
                alert("Erro interno: campos do formulário não encontrados.");
                return;
            }

            const materialId = materialField.value;
            const usuarioId = usuarioField.value;
            const quantidade = quantidadeField.value;
            const observacao = observacaoField.value;

            if (!materialId || !usuarioId || !quantidade) {
                alert("Preencha todos os campos obrigatórios.");
                return;
            }

            const retirada = {
                id: parseInt(id),
                quantidade: parseInt(quantidade),
                observacao: observacao,
                material: { id: parseInt(materialId) },
                usuario: { id: parseInt(usuarioId) }
            };

            fetch(`/material/withdrawal/update/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(retirada)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Retirada atualizada com sucesso!");
                        // Limpa o form e remove o atributo de edição
                        form.reset();
                        form.removeAttribute('data-edit-id');
                        location.reload();
                    } else if (response.status === 404) {
                        alert("Retirada não encontrada.");
                    } else {
                        return response.text().then(text => {
                            throw new Error(text || "Erro ao atualizar a retirada.");
                        });
                    }
                })
                .catch(err => {
                    console.error("Erro:", err);
                    alert("Erro inesperado ao atualizar a retirada.");
                });
        });
    }
});

// Função chamada ao clicar no botão "Editar"
function editarRetirada(botao) {
    const id = botao.getAttribute('data-id');

    fetch(`/material/withdrawal/get/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar a retirada.");
            return response.json();
        })
        .then(retirada => {
            const categoriaSelect = document.getElementById('material-type');
            const materialSelect = document.getElementById('material');
            const usuarioSelect = document.getElementById('resp-withdrawal');
            const quantidadeInput = document.getElementById('quantity');
            const observacaoTextarea = document.getElementById('observation');
            const form = document.getElementById('form-retirada');

            if (!categoriaSelect || !materialSelect || !usuarioSelect || !quantidadeInput || !observacaoTextarea || !form) {
                alert("Erro interno: campo do formulário não encontrado.");
                return;
            }

            categoriaSelect.value = retirada.material.categoria.id;
            materialSelect.value = retirada.material.id;
            usuarioSelect.value = retirada.usuario.id;
            quantidadeInput.value = retirada.quantidade;
            observacaoTextarea.value = retirada.observacao || '';

            // Ativa modo de edição
            form.setAttribute('data-edit-id', retirada.id);
        })
        .catch(error => {
            console.error(error);
            alert("Não foi possível carregar os dados da retirada.");
        });
}
