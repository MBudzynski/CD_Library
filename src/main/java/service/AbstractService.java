package service;


import lombok.RequiredArgsConstructor;
import mapper.DtoMapper;
import repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractService<ENTITY, DTO, Integer> implements CrudService<DTO , Integer> {

    protected final CrudRepository<ENTITY, Integer> repository;
    protected final DtoMapper<ENTITY, DTO> mapper;


    @Override
    public List<DTO> getAll() {
        return repository.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<DTO> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public DTO create(DTO dto) {
        return mapper.toDto(repository.create(mapper.toEntity(dto)));
    }

    @Override
    public DTO update(DTO dto) {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }
    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

}
