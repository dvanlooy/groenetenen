package be.vdab.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;

@ReadOnlyTransactionalService
class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerRepository werknemerRepository;

	WerknemerServiceImpl(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

	@Override
	public Page<Werknemer> findAll(Pageable pageable) {
		return werknemerRepository.findAll(pageable);
	}

}