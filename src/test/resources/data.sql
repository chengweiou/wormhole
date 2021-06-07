set search_path = wormhole;

INSERT INTO reqRecord (url, ip, duration, os, device, browser, status, createAt, updateAt) VALUES
    ('127.0.0.1:60000/wormhole/test', '192.168.1.1', 10, 'mac', 'computer', 'chrome 1.1', 'ok', '2019-01-01T00:00:00', '2019-01-01T00:00:00'),
    ('127.0.0.1:60000/wormhole/test', '192.168.1.2', 10, 'windows', 'phone', 'chrome 1.1', 'ok', '2019-01-01T00:00:00', '2019-01-01T00:00:00');