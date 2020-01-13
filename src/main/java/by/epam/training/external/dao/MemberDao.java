package by.epam.training.external.dao;

import by.epam.training.external.entity.Member;

public interface MemberDao extends GenericDao<Member> {
    void deleteByCrewId(int crewId);
}
