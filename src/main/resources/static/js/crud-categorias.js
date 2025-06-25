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
});
