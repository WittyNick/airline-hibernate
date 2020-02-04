package by.epam.training.external.service;

import by.epam.training.external.dao.CrewDao;
import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.entity.Crew;

public class CrewService {
    private CrewDao crewDao = DaoFactory.getInstance().getCrewDao();

    public void saveCrew(Crew crew) {
        crewDao.save(crew);
    }

    public Crew findCrew(int id) {
        return crewDao.findById(id);
    }

    public void updateCrew(Crew crew) {
        crewDao.update(crew);
    }

    public void deleteCrew(Crew crew) {
        crewDao.delete(crew);
    }
}
