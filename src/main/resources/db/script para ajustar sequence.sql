SELECT setval(
               pg_get_serial_sequence('cozinhas', 'id'),
               COALESCE((SELECT MAX(id) FROM cozinhas), 0) + 1,
               false
       );
