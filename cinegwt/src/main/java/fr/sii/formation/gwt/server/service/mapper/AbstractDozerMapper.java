package fr.sii.formation.gwt.server.service.mapper;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;

/**
 * Implémentation abstraite du mapper avec Dozer
 * 
 */
public abstract class AbstractDozerMapper<E, EDTO> implements MapperInterface<E, EDTO> {

	/** Mapper Dozer, reçu par injection. */
	private Mapper dozerMapper;

	@Override
	public EDTO toDto(E model) {
		if (model == null) {
			return null;
		} else {
			return dozerMapper.map(model, getManagedTypeDTO());
		}
	}

	@Override
	public E toModel(EDTO dto) {
		if (dto == null) {
			return null;
		} else {
			return dozerMapper.map(dto, getManagedType());
		}
	}

	@Override
	public List<EDTO> toDto(List<E> listeModel) {
		List<EDTO> listeDTO = new ArrayList<EDTO>();
		if (listeModel != null && listeModel.size() > 0) {
			for (E e : listeModel) {
				listeDTO.add(toDto(e));
			}
		}
		return listeDTO;
	}

	@Override
	public List<E> toModel(List<EDTO> listeDto) {
		List<E> listeBean = new ArrayList<E>();
		if (listeDto != null && listeDto.size() > 0) {
			for (EDTO eDTO : listeDto) {
				listeBean.add(toModel(eDTO));
			}
		}
		return listeBean;
	}

	@Override
	public Set<EDTO> toDto(Set<E> setModel) {
		Set<EDTO> setDTO = new HashSet<EDTO>();
		if (setModel != null && setModel.size() > 0) {
			for (E e : setModel) {
				setDTO.add(toDto(e));
			}
		}
		return setDTO;
	}

	@Override
	public Set<E> toModel(Set<EDTO> setDto) {
		Set<E> setBean = new HashSet<E>();
		if (setDto != null && setDto.size() > 0) {
			for (EDTO eDTO : setDto) {
				setBean.add(toModel(eDTO));
			}
		}
		return setBean;
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getManagedType() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<E>) pt.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	protected Class<EDTO> getManagedTypeDTO() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<EDTO>) pt.getActualTypeArguments()[1];
	}

	// Getters - setters injection

	public Mapper getDozerMapper() {
		return dozerMapper;
	}

	public void setDozerMapper(Mapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}

}
