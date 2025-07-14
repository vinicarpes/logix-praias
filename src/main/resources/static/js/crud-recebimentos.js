document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('form-recebimento');

    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault();

            const id = form.getAttribute('data-edit-id');

            // Só prosseguir se estiver editando (tem id)
            if (!id) {
                form.submit();  // permite o submit padrão seguir, que será o cadastro via backend
                return;         // evita continuar executando o código JS de edição
            }


            const materialField = document.getElementById('material');
            const usuarioField = document.getElementById('resp-withdrawal');
            const dataRecebimentoField = document.getElementById('receipt-date');
            const fornecedorField = document.getElementById('supplier');
            const quantidadeField = document.getElementById('quantity');
            const descricaoField = document.getElementById('description');

            if (!materialField || !usuarioField || !dataRecebimentoField || !quantidadeField) {
                alert("Erro interno: campos do formulário não encontrados.");
                return;
            }

            const materialId = materialField.value;
            const usuarioId = usuarioField.value;
            const dataRecebimento = dataRecebimentoField.value;
            const fornecedor = fornecedorField?.value || '';
            const quantidade = quantidadeField.value;
            const descricao = descricaoField?.value || '';

            if (!materialId || !usuarioId || !dataRecebimento || !quantidade) {
                alert("Preencha todos os campos obrigatórios.");
                return;
            }

            const recebimento = {
                quantidade: parseInt(quantidade),
                descricao: descricao,
                fornecedor: fornecedor,
                dataRecebimento: dataRecebimento + "T00:00:00",
                material: { id: parseInt(materialId) },
                usuario: { id: parseInt(usuarioId) }
            };

            // Só edição via PUT
            fetch(`/material/receipt/update/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(recebimento)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Recebimento atualizado com sucesso!");
                        form.removeAttribute('data-edit-id');
                        form.reset();
                        location.reload();
                    } else {
                        return response.text().then(text => {
                            throw new Error(text || "Erro ao processar o recebimento.");
                        });
                    }
                })
                .catch(err => {
                    console.error("Erro:", err);
                    alert("Erro inesperado ao processar o recebimento.");
                });
        });
    }
});

// Função chamada ao clicar no botão "Editar"
function editarRecebimento(botao) {
    const id = botao.getAttribute('data-id');

    fetch(`/material/receipt/get/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar o recebimento.");
            return response.json();
        })
        .then(recebimento => {
            const materialSelect = document.getElementById('material');
            const usuarioSelect = document.getElementById('resp-withdrawal');
            const dataRecebimentoInput = document.getElementById('receipt-date');
            const fornecedorInput = document.getElementById('supplier');
            const quantidadeInput = document.getElementById('quantity');
            const descricaoTextarea = document.getElementById('description');
            const form = document.getElementById('form-recebimento');

            if (!materialSelect || !usuarioSelect || !dataRecebimentoInput || !fornecedorInput || !quantidadeInput || !descricaoTextarea || !form) {
                alert("Erro interno: campo do formulário não encontrado.");
                return;
            }

            const categoriaSelect = document.getElementById('material-type');
            if (categoriaSelect && recebimento.material?.categoria) {
                categoriaSelect.value = recebimento.material.categoria.id;
            }

            materialSelect.value = recebimento.material?.id || '';
            usuarioSelect.value = recebimento.usuario?.id || '';
            quantidadeInput.value = recebimento.quantidade || '';
            fornecedorInput.value = recebimento.fornecedor || '';
            descricaoTextarea.value = recebimento.descricao || '';

            if (recebimento.dataRecebimento) {
                const data = new Date(recebimento.dataRecebimento);
                dataRecebimentoInput.value = data.toISOString().split("T")[0];
            }

            form.setAttribute('data-edit-id', recebimento.id);
        })
        .catch(error => {
            console.error(error);
            alert("Não foi possível carregar os dados do recebimento.");
        });
}
