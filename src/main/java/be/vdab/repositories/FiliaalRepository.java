package be.vdab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Filiaal;

public interface FiliaalRepository extends JpaRepository<Filiaal, Long>{
//	void create(Filiaal filiaal);
//
//	Optional<Filiaal> read(long id);
//
//	void update(Filiaal filiaal);
//
//	void delete(long id);
//
//	List<Filiaal> findAll();
//
//	long findAantalFilialen();

	List<Filiaal> findByAdresPostcodeBetweenOrderByNaam(int van, int tot);
	//Spring-data auto generated query "select f from Filiaal f where adres.postcode between :van and :tot order by naam"
}