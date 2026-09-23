"use client"

import { useState, SyntheticEvent} from "react";
import { useRouter } from "next/navigation";

export default function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    async function handleSubmit(e: SyntheticEvent<HTMLFormElement>) {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
          const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
          });
          
          if (!response.ok) {
            const data = await response.json();
            setError(data.error || "An error occurred");
            return;
          }
          const data = await response.json();

        
          if (data.role === "ADMIN") router.push("/admin");
          else if (data.role === "STUDENT") router.push("/students");
          else if (data.role === "INSTRUCTOR") router.push("/instructors");
      
        } finally {
          setLoading(false);
        }
    }

    return (
    <div className="flex min-h-screen items-center justify-center">
      <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-80">
        <h1 className="text-xl font-bold">Faça seu login</h1>

        {error && <p className="text-red-600 text-sm">{error}</p>}

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="border rounded p-2"
          disabled={loading}
        />

        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="border rounded p-2"
          disabled={loading}
        />

        <button type="submit" className="bg-blue-600 text-white rounded p-2" disabled={loading}>
          {loading ? "Entrando..." : "Entrar"}
        </button>
      </form>
    </div>
  )
}