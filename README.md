# What?

- RESTful CRUD API for a social network

# Technologies used

- Spring, Spring Boot, PostgreSQL and Hibernate

# API endpoints

## User
| Method | Action            | Route              | Body                   | Implemented          |
| ------ | ----------------- | ------------------ | ---------------------- | -------------------- |
| POST   | add new user      | /app/user          | [example](user-post)   | :heavy_check_mark:   |
| GET    | get all users     | /app/user          |                        | :heavy_check_mark:   |
| GET    | get user by id    | /app/user/{userId} |                        | :heavy_check_mark:   |
| UPDATE | update user by id | /app/user/{userId} | [example](user-update) | :heavy_check_mark:   |
| DELETE | delete user by id | /app/user/{userId} |                        | :heavy_check_mark:   |

## Friend
| Method | Action                  | Route                                | Body                             | Implemented          |
| ------ | ----------------------- | ------------------------------------ | -------------------------------- | -------------------- |
| GET    | get all friends of user | /app/user/{userId}/friend            |                                  | :heavy_check_mark:   |
| PUT    | add new friend          | /app/user/friend                     | [example](friend-add-new-friend) | :heavy_check_mark:   |
| DELETE | delete friend by id     | /app/user/{userId}/friend/{friendId} |                                  | :heavy_check_mark:   |

## Hobby
| Method | Action                  | Route      | Body | Implemented        |
| ------ | ----------------------- | ---------- | ---- | ------------------ |
| GET    | get all hobbies         | /app/hobby |      | :heavy_check_mark: |

## Recommend a friend
| Method | Action                                                           | Route                                                         | Body | Implemented        |
| ------ | ---------------------------------------------------------------- | ------------------------------------------------------------- | ---- | ------------------ |
| GET    | get friends of friends                                           | /app/recommend-friend/2nd-level-friend/{userId}               |      | :heavy_check_mark: |
| GET    | get 3rd level friends                                            | /app/recommend-friend/3rd-level-friend/{userId}               |      |                    |
| GET    | users with at least 1 common hobby                               | /app/recommend-friend/nonfriends-with-common-hobbies/{userId} |      | :heavy_check_mark: |
| GET    | users with at least 1 common hobby and are 2nd level friends     | /app/recommend-friend/hobby-2nd-level-friend                  |      |                    |
| GET    | GET users with at least 1 common hobby and are 3rd level friends | /app/recommend-friend/hobby-3rd-level-friend                  |      |                    |

## Post
| Method | Action       | Route | Body | Implemented |
| ------ | ------------ | ----- | ---- | ----------- |
| POST   | add new post |       |      |             |

## Home feed
| Method | Action                                                        | Route              | Body | Implemented        |
| ------ | ------------------------------------------------------------- | ------------------ | ---- | ------------------ |
| GET    | get all posts                                                 |                    |      |                    |
| GET    | get all posts of user's friends                               | /app/home/{userId} |      | :heavy_check_mark: |
| GET    | get all posts of user's friends ranked by number of reactions |                    |      |                    |

# Todo
## Possible new features
- Link users account to their Spotify
    - Recommend friends by Spotify liked songs
- Authentication
- Pictures in posts
- Profile pictures and default profile pictures
- Add reactions to posts
- Add comments
- Add replies (nested comments)
- Add hashtag to posts

## Possible improvements
- Unit tests
- Data validation
- Hibernate improvements

# Use the app
- To generate data using PostgreSQL, use SQL script in `generate data` directory.

### <a id="user-post">/app/user</a>
```json
{
  "firstName": "Muhammad",
  "lastName": "Ihab",
  "email": "m.ihab@mail.com",
  "userPassword": "123",
  "dob": "2022-01-01"
}
```

### <a id="user-update">/app/user</a>
```json
{
  "id": 11,
  "lastName": "new name"
}
```
_attributes: firstName, lastName, email and password_

### <a id="friend-add-new-friend">/app/user/friend</a>
```json
{
  "userId": 11,
  "friendId": 1
}
```
