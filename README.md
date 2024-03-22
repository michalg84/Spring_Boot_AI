# A Spring Boot REST LLM Chat Client for LLM model run locally with Ollama framework
It uses Spring AI as interface to interact with many LLM Models and framworks.
This project uses Ollama framework run locally, but you can easily make it work with OpenAi and others (see [Spring AI docs](https://docs.spring.io/spring-ai/reference/api/chatclient.html)).
## Setup
To make it work:
1. Download [Ollama](https://ollama.com/download) and start locally (cmd:> ollama serve)
2. Pull LLM such as LLama2, Mistral, Gemma etc from [Ollama models](https://ollama.com/library) (example: cmd:> ollama pull gemma:2b)
3. Start Spring Boot App in IDE or package and deply to Tomcat server

## Play with it
> [!IMPORTANT]
> Take into consideration that running and processing question is both time and GPU/CPU resource consuming. It takes between 10-50 seconds to anwser simple question such as "How much is 2 + 4? "or "What is a boat?" with small models (gemma:2b - 2 Bilion parameters) and standard developer laptop with business class GPU model.

Invoke one of two endoints:
  - simple String question as bellow or use [example](https://github.com/michalg84/Spring_Boot_AI/blob/main/src/main/resources/static/asksimplequestion.http)
```
    POST http://localhost:8080/api/ollama/asksimplequestion
    Content-Type: application/json
    
    {
      "question": "How much is 2 + 4 ?"
    }
```

  - more advanced prompt as bellow or use [example](https://github.com/michalg84/Spring_Boot_AI/blob/main/src/main/resources/static/askquestion.http)
```
    POST http://localhost:8080/api/ollama/askquestion
    Content-Type: application/json
  
    {
      "model": "gemma:2b",
      "messages": [
        {
          "content": "How much is anything divided by zero?",
          "role": "user"
        },
        {
          "content": "Can we divide by zero in other mathematical systems than Decimal System?",
          "role": "user"
        }
      ],
      "stream": false
    }
```


