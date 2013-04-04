package fr.sii.formation.gwt.server.service.mapper;

import java.util.List;
import java.util.Set;

public interface MapperInterface<E, EDTO> {

	E toModel(EDTO dto);

	EDTO toDto(E model);

	List<E> toModel(List<EDTO> listeDto);

	List<EDTO> toDto(List<E> listeModel);

	Set<E> toModel(Set<EDTO> setDto);

	Set<EDTO> toDto(Set<E> setModel);
}