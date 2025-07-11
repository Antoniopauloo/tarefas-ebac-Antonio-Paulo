package br.com.jeb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    TesteClienteTest.class,
    ClienteServiceTest.class, 
    ContratoServiceTest.class 
})
public class AllTests { }
