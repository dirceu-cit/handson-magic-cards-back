package magiccards.repositories;

import magiccards.JpaConfiguration;
import magiccards.entities.Expansion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { JpaConfiguration.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@DataJpaTest
public class ExpansionRepositoryTest {

    @Resource
    private ExpansionRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;
    private Integer idExpansion1;
    private Expansion givenExpansion1;
    private Integer givenExpansion2;
    private Expansion givenExpansion3;
    private Date now = new Date();

    @Before
    public void setUp(){
        // given
        givenExpansion1 = new Expansion(1000,"test1",
                "test1","test1","test1", 501,true,true,now);
        idExpansion1 = testEntityManager.persistAndFlush(givenExpansion1).getExpansionId();
        givenExpansion2 = testEntityManager.persistAndFlush(new Expansion(1001,"test2",
                "test2","test2","test2",501,false,false,now)).getExpansionId();
        givenExpansion3 = new Expansion(1003,"test3",
                "test3","test3", "test3now",512,true,true,now);
    }

    @Test
    public void search_sucess() {
        // then
        Expansion response =  repo.findOne(idExpansion1);
        validateReponse(givenExpansion1,response);
        
    }

    private void validateReponse(Expansion expected,Expansion response) {
        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getCode(), response.getCode());
        assertEquals(expected.getLinkName(), response.getLinkName());
        assertEquals(expected.getPtBrName(), response.getPtBrName());
        assertEquals(expected.getLegal(), response.getLegal());
        assertEquals(expected.getPromo(), response.getPromo());
        assertEquals(expected.getExpansionCategoryId(), response.getExpansionCategoryId());
        assertEquals(expected.getLaunchDate(), response.getLaunchDate());
    }

    @Test
    public void delete_sucess(){
        //given
        repo.delete(givenExpansion2);
        //then
        assertEquals(null, repo.findOne(givenExpansion2));
    }

    @Test
    public void create_sucess(){
        Integer id3 = repo.save(givenExpansion3).getExpansionId();
        assertEquals("test3", repo.findOne(id3).getName());

    }

}