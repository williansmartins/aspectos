package br.com.fiap.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidaData
{

    public boolean isWithinRange( Date testDate )
    {
	Date startDate = new Date( 2013 - 1900, 02 - 1, 01 ); // year1-1900,
							      // month1-1, day1
	Date endDate = new Date( 2013 - 1900, 02 - 1, 27 );

	return !( testDate.before( startDate ) || testDate.after( endDate ) );
    }

    public boolean comprouEm5Anos( Date testDate )
    {
	Date hoje = new Date();
	Calendar c1 = Calendar.getInstance();
	c1.setTime( hoje );
	c1.add( Calendar.YEAR, -5 );
	Calendar c2 = Calendar.getInstance();
	c2.setTime( hoje );
	final Date haCincoAnos = c1.getTime();
	final Date diaDeHoje  = c2.getTime();

	return ( testDate.before(diaDeHoje) && testDate.after(haCincoAnos) );
    }

    public static void main( String[] args )
    {
	System.out.println( new ValidaData().comprouEm5Anos( new Date( 2010 - 1900, 02, 20 ) ) );
    }
}
