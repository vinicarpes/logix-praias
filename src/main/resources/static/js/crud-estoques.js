async function loadMaterialsByCategory() {
    const categoriaSelect = document.getElementById('categoria');
    const materialSelect = document.getElementById('material');
    const categoriaId = categoriaSelect.value;

    // Limpa opções atuais do material
    materialSelect.innerHTML = '<option selected disabled value="">Carregando...</option>';

    if (!categoriaId) {
        materialSelect.innerHTML = '<option selected disabled value="">Selecione o material</option>';
        return;
    }

    try {
        const response = await fetch(`/material/by-category?categoriaId=${categoriaId}`);
        if (!response.ok) throw new Error('Erro ao buscar materiais');

        const materiais = await response.json();

        // Limpa as opções
        materialSelect.innerHTML = '<option selected disabled value="">Selecione o material</option>';

        materiais.forEach(mat => {
            const option = document.createElement('option');
            option.value = mat.id;  // Ajuste conforme o nome do campo id no JSON
            option.textContent = mat.nome || mat.toString || mat; // adapte se precisar
            materialSelect.appendChild(option);
        });

    } catch (error) {
        materialSelect.innerHTML = '<option selected disabled value="">Erro ao carregar materiais</option>';
        console.error(error);
    }
}

// Opcional: chamar quando a página carregar e categoria já estiver selecionada
document.addEventListener('DOMContentLoaded', () => {
    const categoriaSelect = document.getElementById('categoria');
    if (categoriaSelect.value) {
        loadMaterialsByCategory();
    }
});