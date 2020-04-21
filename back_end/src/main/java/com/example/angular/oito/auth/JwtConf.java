package com.example.angular.oito.auth;

public class JwtConf {

	public static final String  LLAVE_SECRETA= "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEogIBAAKCAQEAvR2uAx7Xjx5w8/Z5bS52yuOplS/80ff+pIi2Qfsl949w5y/y\n" + 
			"CkTGKp2RPwDX7vqjH7IYrpVPt9Tnea9+IRXMeBXy7RQY5eW44Mvl9ROli23Ha6Rz\n" + 
			"bw18zBhwf+mlXHeyKpjYJPajf/NDb7VZUso+9Cn4hCPmzqpwM978i43RRlGGNqPu\n" + 
			"dQ4OY4swjaAQJJOH1EGYhiNYGZVOUWr7HymFISwFnXZb5RNuJMPDptPB9HmJEpiJ\n" + 
			"fwIcu9IpeE5k/4RNGN1rV9ZFOmmAdu0LhvQukga5GLLGBTyftM8DyymSF4sLvECg\n" + 
			"KTt8JvloPnrtFE1yhbC+MILn25VpXvIYxKwctwIDAQABAoIBAAMrHiE7QQ1Ky0Wr\n" + 
			"GXm0z5Syn8K6HRg/eWq0R6lvVcGCC7gz23BEmrVYEnjlwLhmRplfWH6ETeOck4w8\n" + 
			"XnabcOOKKpnCPnCLmr9Zay7h92IQosiw5qYzqaWZuzjJZ1XXQ4rnKKfl4fNg1f5n\n" + 
			"IWn7rXZVxa4hE9rejCayU7Ta2Wi+KYY/Htln4/aUnFqyOJ7z+TCBQ1pLB9nJgQjK\n" + 
			"/pAjlpYtBQObL6bemxkA55oO/TMx+YY18H7RfDKcUuQIOs8mHJyUkDQinwcmIjSw\n" + 
			"meuntq8i4vpH1MeUy1D5gtPhtyERbljR7Fu+WvZkhU4uszVPBaZwNI/i5dLIKA0J\n" + 
			"LJYyqDECgYEA7fLlYvHuFpAHj6+/WgOI6GO5TmJ/qEeyG9uYHjWUc88I8EO6HBsv\n" + 
			"rrfAJhzdLqqP0mhpgU+oXVJbTboSedLC6tMT//1FqZvVPfBPM5hOvRLsJQvNfobI\n" + 
			"nx/8vQEZoOCaEsVljJHIKYlLqHCNLdyq7tD0hhPeoMArgKQHq7xg0F0CgYEAy3Zr\n" + 
			"wQOm4Ga7XeF5Wf5lhL+ywZ24own+hHXRJduO1ifLm3/s6uibAyF4+4Hl9hoTg1QK\n" + 
			"9knvMaI7SbUc8lByKxZqk8JbpP/D+R/Xhc2fxMQ3dma0EgGVXeVMfmu+Gohp4Zcz\n" + 
			"ZjRtj/5S5AMV/bMmuIYjgUzL+yyr7gw7poi0ICMCgYBfcRD/KgkUtbrCsHyxLz/H\n" + 
			"7tASF8Iajn9rWx5d56He/ckZDiFlSqZKj1ucLfGt2S4M+GJxQrFGlVUYDS2B/b6/\n" + 
			"D+EyybUafe3/hRdPm6Thg+iCR4SqXoe0A9GNQha0h8JlCxZbVtIRjwdwzYxjO+K4\n" + 
			"HcomyWt/0PTiCWkmQk4W5QKBgDcvt6pywyC6vLeKINA4IHERTfsWPK58V3y2ODR8\n" + 
			"iRGtWZyj5i8RSHpg6d/OLAHEsOY+NXr4MtjTmCE17tQK3eyx99Z8MT9f4FgCOwMf\n" + 
			"8uX8Es8arFz18P225DimILoxpgZ6bIacUdtssC8IZz6ZCTejqp23ptxKEZhP2R4i\n" + 
			"ljjhAoGAYkaeX/hzewdlODZ7dVyZOFb5l1ZIG7CtBM1dNk5ADNRTIhxQSqxxvfv0\n" + 
			"wk9EKQA3j4JzKUpJQbIWqExbVFCx8O+faB8jF4lOIW76biF6z7OF/LKGJeTMfTku\n" + 
			"WB8u5rrRKOne8AFp+HeNEDwJiBzDqVuA6KT8fyzcKm1sqkUzPlQ=\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvR2uAx7Xjx5w8/Z5bS52\n" + 
			"yuOplS/80ff+pIi2Qfsl949w5y/yCkTGKp2RPwDX7vqjH7IYrpVPt9Tnea9+IRXM\n" + 
			"eBXy7RQY5eW44Mvl9ROli23Ha6Rzbw18zBhwf+mlXHeyKpjYJPajf/NDb7VZUso+\n" + 
			"9Cn4hCPmzqpwM978i43RRlGGNqPudQ4OY4swjaAQJJOH1EGYhiNYGZVOUWr7HymF\n" + 
			"ISwFnXZb5RNuJMPDptPB9HmJEpiJfwIcu9IpeE5k/4RNGN1rV9ZFOmmAdu0LhvQu\n" + 
			"kga5GLLGBTyftM8DyymSF4sLvECgKTt8JvloPnrtFE1yhbC+MILn25VpXvIYxKwc\n" + 
			"twIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
	
	
	
}
