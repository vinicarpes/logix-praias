package br.ifsc.edu.fln.logixpraias.repository;

import br.ifsc.edu.fln.logixpraias.model.Estoque;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EstoqueSpecification {

    public static Specification<Estoque> temMaterial(Long materialId) {
        return (root, query, cb) ->
                materialId == null ? null : cb.equal(root.get("material").get("id"), materialId);
    }

    public static Specification<Estoque> temCategoria(Long categoriaId) {
        return (root, query, cb) ->
                categoriaId == null ? null : cb.equal(root.get("material").get("categoria").get("id"), categoriaId);
    }

    public static Specification<Estoque> quantidade(String operacao, Integer valor) {
        return (root, query, cb) -> {
            if (valor == null || operacao == null) return null;

            switch (operacao) {
                case "menor": return cb.lt(root.get("quantidade"), valor);
                case "maior": return cb.gt(root.get("quantidade"), valor);
                case "igual": return cb.equal(root.get("quantidade"), valor);
                default: return null;
            }
        };
    }
}

