<h2>A Basic Spring Boot REST LLM Chat Client for LLM run localy with Ollama framefork</h2>

To make it work:
1. Download Ollama and start locally
2. Pull LLM  as LLama2, Mistral, Gemma etc: cmd:> ollama pull gemma:2b
3. Start Spring Boot App in IDE or package and deply to Tomcat server
4. Invoke one of two endoints:
  - Simple String https://github.com/michalg84/Spring_Boot_AI/blob/main/src/main/resources/static/asksimplequestion.http
    POST http://localhost:8080/api/ollama/asksimplequestion
    Content-Type: application/json
    
    {
      "question": "How much is 2 + 4 ?"
    }

  - Prompt https://github.com/michalg84/Spring_Boot_AI/blob/main/src/main/resources/static/askquestion.http
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
