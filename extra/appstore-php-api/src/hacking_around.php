<?php 

$api = new SoapClient( "http://10.255.1.81:8080/appstore/api?wsdl", array( 'trace' => true, 'cache_wsdl' => 0 ) );

$api->__getFunctions();

//$api->login( "admin", "test", "123qwe" );
$api->login( "devuser", "test", "123qwe" );

echo $api->test();

//$api->addAppToPlatform( 2, 1 );

//$api->addAppToCategory( 2, 3 );

//print_r( $api->getAppsByUserId( 2 ) );

//header( 'Content-Type: application/x-tar-gz' );
//header( 'Content-Disposition: attachment; filename="download.tar.gz"' );
//print $api->download( 3 );

$api->logout();
?>