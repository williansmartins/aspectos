package br.com.fiap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidaData
{

    public boolean isWithinRange( Date testDate )
    {
	Date startDate = new Date( 2013-1900, 02-1, 01 ); //year1-1900, month1-1, day1
	Date endDate = new Date( 2013-1900, 02-1, 27 );

	return !( testDate.before( startDate ) || testDate.after( endDate ) );
    }

    public static void main( String[] args )
    {
	System.out.println( new ValidaData().isWithinRange( new Date(2013, 02, 20) ) );
    }
}
