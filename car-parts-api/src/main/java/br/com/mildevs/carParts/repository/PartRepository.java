package br.com.mildevs.carParts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mildevs.carParts.enumeration.Category;
import br.com.mildevs.carParts.model.Part;

public interface PartRepository extends JpaRepository<Part, Long> {
    
    Part findByBarCode(long barCode);

    boolean existsByBarCode(long barCode);

    List<Part> findByNameLike(String initialChar);

    List<Part> findByCarModel(String carModel);

    List<Part> findByCategory(Category category);
    
}