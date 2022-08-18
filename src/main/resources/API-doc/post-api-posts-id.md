# 게시글 비밀번호 확인하기

### Method : **`POST`**

### URI : `/api/posts/{id}`

<br>

## Request

### Body

**Parameter**

| Name | Type | Description | Required|
|--|--|--|--|
| password | `String` | 게시글 비밀번호 | O |

<br>

## Response

| Name | Type | Description | Required |
|--|--|--|--|
| success | `Boolean` | 요청의 성공 여부 |  O |
| Message | `String` | 사용자에게 보여줄 메시지 |  O |
| error | `String` | 에러에 관한 내용 반환 | X |

<br>

## Sample

### Request
```json
{
  "password":"1234"
}
```

### Response
```json
{
  "success": true,
  "data": true,
  "message": "올바른 비밀번호입니다.",
  "error": null
}
```

