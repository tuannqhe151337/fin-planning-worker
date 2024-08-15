package com.example.planning_worker.repository.impl;

import com.example.planning_worker.entity.Currency;
import com.example.planning_worker.repository.CustomCurrencyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CurrencyRepositoryImpl implements CustomCurrencyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Currency> getCurrencyWithPagination(String query, Pageable pageable) {
        // HQL query
        String hql = " SELECT currency FROM Currency currency " +
                " WHERE currency.name like :query " +
                " AND currency.isDelete = false " +
                " ORDER BY ";

        // Handling sort by and sort type
        List<Sort.Order> sortOrderList = pageable.getSort().get().toList();
        for (int i = 0; i < sortOrderList.size(); i++) {
            Sort.Order order = sortOrderList.get(i);

            String sortType = order.getDirection().isAscending() ? "asc" : "desc";
            switch (order.getProperty().toLowerCase()) {
                case "name":
                    hql += "currency.name " + sortType;
                    break;
                default:
                    hql += "currency.id " + sortType;
            }

            if (i != sortOrderList.size() - 1) {
                hql += ", ";
            } else {
                hql += " ";
            }
        }

        // Run query
        return entityManager.createQuery(hql, Currency.class)
                .setParameter("query", "%" + query + "%")
                .setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize()) // We can't use pagable.getOffset() since they calculate offset by taking pageNumber * pageSize, we need (pageNumber - 1) * pageSize
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

}
