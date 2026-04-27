-- ============================================================
-- Dades inicials sintètiques per al fòrum
-- Aquest fitxer s'executa automàticament en arrencar l'aplicació
-- gràcies a spring.sql.init.mode=always i ddl-auto=create-drop
-- ============================================================

-- Publicacions
INSERT INTO publicacio (nick_que_publica, text, data_publicacio, paraules_clau) VALUES
('usuari_joan',    'Quina és la millor manera d''aprendre Spring Boot des de zero?',                        '2024-01-10 09:15:00', 'spring,java,aprenentatge'),
('maria_dev',      'Comparativa entre MySQL i PostgreSQL per a projectes petits.',                          '2024-01-12 11:30:00', 'mysql,postgresql,bbdd'),
('tech_pere',      'He trobat un bug estrany amb Hibernate i les relacions LazyLoading.',                   '2024-01-15 16:00:00', 'hibernate,jpa,bug'),
('anna_coder',     'Recomaneu alguna llibreria Java per a generació de PDF?',                               '2024-01-18 10:45:00', 'java,pdf,llibreria'),
('jordi_backend',  'Com gestionar excepcions de forma global en Spring Boot amb @ControllerAdvice?',        '2024-01-20 14:20:00', 'spring,excepcions,rest'),
('marta_dev',      'Tutorial pas a pas per crear una API REST amb HATEOAS.',                                '2024-01-22 09:00:00', 'hateoas,api,rest,spring'),
('carles_it',      'Diferències entre @Service, @Component i @Repository a Spring.',                       '2024-01-25 17:10:00', 'spring,annotations,arquitectura'),
('laura_full',     'Com optimitzar consultes JPA amb @Query i projeccions?',                                '2024-01-28 12:00:00', 'jpa,query,optimitzacio'),
('pau_dev',        'Quin IDE recomaneu per a desenvolupament Java el 2024?',                                '2024-02-01 08:30:00', 'ide,java,eines'),
('neus_backend',   'Bones pràctiques per a la gestió de versions d''una API REST.',                         '2024-02-05 15:45:00', 'api,versioning,rest');

-- Comentaris (publicacio_id fa referència a l'ordre d'inserció: 1..10)
INSERT INTO comentari (nick_comenta, text, valoracio, data_comentari, publicacio_id) VALUES
('maria_dev',      'Jo vaig aprendre amb la documentació oficial i un projecte petit. Molt recomanable!',   5, '2024-01-10 10:00:00', 1),
('tech_pere',      'Mira els cursos de Baeldung, són molt complets.',                                       4, '2024-01-10 11:00:00', 1),
('anna_coder',     'A mi em va anar molt bé fer un projecte real des del principi.',                        5, '2024-01-10 13:00:00', 1),

('usuari_joan',    'Per a projectes petits jo prefereixo MySQL per la facilitat de configuració.',          4, '2024-01-12 12:00:00', 2),
('jordi_backend',  'PostgreSQL té millor suport per a JSON si en necessites.',                              3, '2024-01-13 09:30:00', 2),

('maria_dev',      'Aquest bug el vaig tenir jo. Solució: usar @Transactional al servei.',                  5, '2024-01-15 17:00:00', 3),
('laura_full',     'Afegeix FetchType.EAGER temporalment per depurar, t''ajudarà a veure el problema.',    3, '2024-01-16 10:00:00', 3),

('carles_it',      'iText és molt potent per a PDF, però té llicència. Considera OpenPDF que és gratuït.', 4, '2024-01-18 11:30:00', 4),
('pau_dev',        'Apache PDFBox és una altra bona opció de codi obert.',                                  4, '2024-01-19 08:00:00', 4),

('neus_backend',   '@ControllerAdvice és perfecte. Jo el combino amb classes d''error personalitzades.',   5, '2024-01-20 15:00:00', 5),
('marta_dev',      'Molt bona pregunta. Afegeix també @ResponseStatus per controlar els codis HTTP.',       4, '2024-01-21 09:00:00', 5),

('usuari_joan',    'Gràcies pel tutorial! M''ha ajudat molt a entendre EntityModel i CollectionModel.',     5, '2024-01-22 10:00:00', 6),
('tech_pere',      'Podrieu afegir un exemple amb PagedResourcesAssembler?',                                3, '2024-01-23 11:00:00', 6),

('anna_coder',     '@Service és per a la lògica de negoci, @Repository per a accés a dades. @Component és genèric.', 5, '2024-01-25 18:00:00', 7),

('jordi_backend',  'Les projeccions amb interfícies són molt eficients, eviten carregar tot l''objecte.',   4, '2024-01-28 13:00:00', 8),
('maria_dev',      'Combina @Query amb Pageable per a resultats paginats. Funciona molt bé.',               5, '2024-01-29 10:00:00', 8),

('carles_it',      'IntelliJ IDEA és el millor de lluny per a Java, tot i que és de pagament.',             4, '2024-02-01 09:00:00', 9),
('laura_full',     'VS Code amb extensions Java és gratuït i sorprenentment potent.',                       3, '2024-02-02 10:30:00', 9),
('neus_backend',   'Eclipse és gratuït i madur, tot i que una mica pesat.',                                 2, '2024-02-03 11:00:00', 9),

('pau_dev',        'Jo uso el prefix /api/v1/, /api/v2/ als endpoints. Simple i efectiu.',                  4, '2024-02-05 16:00:00', 10),
('marta_dev',      'Headers de versió (Accept: application/vnd.api.v1+json) és més net però més complex.', 3, '2024-02-06 09:00:00', 10);
