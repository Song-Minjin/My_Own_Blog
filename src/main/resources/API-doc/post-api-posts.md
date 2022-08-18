# 게시글 작성하기

### Method : **`POST`**

### URI : `/api/posts`

<br>

## Request

### Body

**Parameter**

| Name | Type | Description | Required|
|--|--|--|--|
| title | `String` | 게시글 제목 | O |
| author | `String` | 게시글 작성자 | O |
| content | `String` | 게시글 내용 | O |
| password | `String` | 게시글 비밀번호 | O |

<br>

## Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| data | `Boolean` | 비밀번호 일치 여부 |  O |
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

## Sample

### Request
```json
{
  "title":"김영희의 TIL",
  "author":"김영희",
  "content":"ㅎㅎ",
  "password":"1234"
}
```

### Response
```json
{
  "success": true,
  "message": "글 작성이 완료되었습니다.",
  "error": null
}
```

