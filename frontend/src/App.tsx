import './App.css'
import { Button } from '@/components/ui/button'
import { ThemeProvider } from './providers/ThemeProvider'
import { ModeToggle } from './components/ui/theme-toggler'

function App() {

  return (
    <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
      <h1 className="text-3xl font-bold underline">
        Hello world!
        <Button>Button</Button>

        <ModeToggle />
      </h1>
    </ThemeProvider>
  )
}

export default App
