package service;

import java.util.List;
import java.util.Optional;

public interface CrudService<DTO , Integer> {

    List<DTO> getAll();

    Optional<DTO> findById(Integer id);

    DTO create(DTO dto);

    DTO update(DTO dto);

    void delete(Integer dto);
}
