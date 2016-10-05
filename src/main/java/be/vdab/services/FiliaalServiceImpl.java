package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.mail.MailSender;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@ReadOnlyTransactionalService
class FiliaalServiceImpl implements FiliaalService {
	private final FiliaalRepository filiaalRepository;
	private final MailSender mailSender;

	FiliaalServiceImpl(FiliaalRepository filiaalRepository, MailSender mailSender) {
		this.filiaalRepository = filiaalRepository;
		this.mailSender = mailSender;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal, String urlAlleFilialen) {
		filiaalRepository.save(filiaal);
		mailSender.nieuwFiliaalMail(filiaal,
				urlAlleFilialen + '/' + filiaal.getId());
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return Optional.ofNullable(filiaalRepository.findOne(id));
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		// if (filiaalRepository.findAantalWerknemers(id) != 0) {
		// throw new FiliaalHeeftNogWerknemersException();
		// }
		// filiaalRepository.delete(id);

		Optional<Filiaal> optionalFiliaal = Optional.ofNullable(filiaalRepository.findOne(id));
		if (optionalFiliaal.isPresent()) {
			if (!optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalRepository.delete(id);
		}
	}

	@Override
	public List<Filiaal> findNietAfgeschreven() {
		return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(Iterable<Filiaal> filialen) {
		for (Filiaal filiaal : filialen) {
			filiaal.afschrijven();
		}
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanpostcode(), reeks.getTotpostcode());
	}
	
	@Override
	@Scheduled(/*cron = "0 0 1 * * *"*/ fixedRate=60000) // test = om de minuut
	public void aantalFilialenMail() {
//	mailSender.aantalFilialenMail(filiaalRepository.count()); //effe genoeg mails gezien
	}
}
