<?php
require_once '../vendor/autoload.php';
require_once 'class-db.php';

define('GOOGLE_CLIENT_ID', '458528637855-uc9c5mh7cjm0qm54oii9ge4cmisda6rh.apps.googleusercontent.com');
define('GOOGLE_CLIENT_SECRET', 'K3NfhdjoCJOkAHiSOHhoR4aU');

$config = [
    'callback' => 'http://localhost/virtualSchool/upload/callback.php',
    'keys'     => [
        'id' => GOOGLE_CLIENT_ID,
        'secret' => GOOGLE_CLIENT_SECRET
    ],
    'scope'    => 'https://www.googleapis.com/auth/youtube.upload',
    'authorize_url_parameters' => [
        'approval_prompt' => 'force', // to pass only when you need to acquire a new refresh token.
        'access_type' => 'offline'
    ]
];

$adapter = new Hybridauth\Provider\Google($config);
