package com.example.demo.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import com.example.demo.entity.Lector;
import org.springframework.data.repository.CrudRepository;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LectorRepositoryTest {
  @Autowired
//  private LectorRepository lectorRepository;
  @Before
  public void setUp() throws Exception {
//    Lector user1= new Lector("Куприянов","Алексей", "Алексей", "Алексей", "Алексей", "Алексей");
//    Lector user2= new Lector("Куприянов2","Алексей", "Алексей", "Алексей", "Алексей", "Алексей");
//
//    //save user, verify has ID value after save
//    assertNull(user1.getId());
//    assertNull(user2.getId());//null before save
//    this.lectorRepository.save(user1);
//    this.lectorRepository.save(user2);
//    assertNotNull(user1.getId());
//    assertNotNull(user2.getId());
  }

  @Test
  public void testFetchData(){
        /*Test data retrieval*/
//    Lector userA = lectorRepository.findByFirstname("Алексей");
//    assertNotNull(userA);
//    assertEquals(38, userA.getFirstname());
  }
}
