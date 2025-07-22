-- 这个脚本的版本是 V2
-- 使用你通过 PasswordGenerator 生成的那个哈希值
INSERT INTO users (username, password, name, avatar_url)
VALUES ('admin', '$2a$10$GwlTLgXy3ZN3Zih8UyXfX.enZwuOWqOYftJMYYGyfwhP21.lALGMq', '数据库管理员', 'https://i.pravatar.cc/150?u=db-admin');